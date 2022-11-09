const express = require('express')
const app = express()
const port = 3000

const config = {
    host: 'db',
    user: 'root',
    password: 'root',
    database: 'nodedb'
};

const mysql = require('mysql')



app.get('/', (req, res) => {
    const connection = mysql.createConnection(config)

    console.log(req.query.name)

    var name = req.query.name ? req.query.name : "Giovani"

    const sql = `INSERT INTO people(name) values('${name}')`

    connection.query(sql)

    var retorno = '<h1>Full Cycle Rocks!</h1>'

    const sqlSelect = `SELECT * FROM people`

    connection.query(sqlSelect, function (err, result, fields) {
        if (err) {

            retorno = '<h1>Erro na consulta com o banco de dados</h1>'
            res.send(retorno)

            throw err;
        }

        result.map(people => {
            retorno = retorno + '<li>' + people.name + '</li>';
        })

        res.send(retorno)


    })

    connection.end()

})



app.listen(port, () => {
    console.log('Rodando na porta ' + port)
})