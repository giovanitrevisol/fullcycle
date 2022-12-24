package gateway

import "github/vaguiiinho/fc-ms-walletcore/internal/entity"

type TransactionGateway interface {
	Create(transaction *entity.Transaction) error
}
