package database

import (
	"database/sql"
	"github/vaguiiinho/fc-ms-walletcore/internal/entity"
	"testing"

	"github.com/stretchr/testify/suite"
)

type AccountDBTestSuite struct {
	suite.Suite
	db        *sql.DB
	accountDB *AccountDB
	client    *entity.Client
}

func (s *AccountDBTestSuite) SetupSuite() {
	db, err := sql.Open("sqlite3", ":memory:")
	s.Nil(err)
	s.db = db
	db.Exec("Create table clients (id varchar(255), name varchar(255), email varchar(255), created_at date)")
	db.Exec("Create table accounts (id varchar(255), client_id varchar(255), balance int, created_at date)")
	s.accountDB = NewAccountDB(db)
	s.client, _ = entity.NewClient("john", "j@j.com")
}

func (s *AccountDBTestSuite) TearDownSuite() {
	defer s.db.Close()
	s.db.Exec("DROP TABLE clients")
	s.db.Exec("DROP TABLE accounts")
}

func TestAccountDBTestSuite(t *testing.T) {
	suite.Run(t, new(AccountDBTestSuite))
}

func (s *AccountDBTestSuite) TestSave() {
	Account := entity.NewAccount(s.client)
	err := s.accountDB.Save(Account)
	s.Nil(err)

}

func (s *AccountDBTestSuite) TestFindByID() {
	s.db.Exec("INSERT INTO clients(id, name, email, created_at) VALUES (?,?,?,?)",
		s.client.ID, s.client.Name, s.client.Email, s.client.CreatedAt,
	)
	account := entity.NewAccount(s.client)
	err := s.accountDB.Save(account)
	s.Nil(err)
	AccountDB, err := s.accountDB.FindByID(account.ID)
	s.Nil(err)
	s.Equal(account.ID, AccountDB.ID)
	s.Equal(account.Client.ID, AccountDB.Client.ID)
	s.Equal(account.Balance, AccountDB.Balance)
	s.Equal(account.Client.Email, AccountDB.Client.Email)
}
