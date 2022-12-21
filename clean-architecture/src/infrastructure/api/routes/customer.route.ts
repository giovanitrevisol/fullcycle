import express, { Request, Response } from "express";
import CreateCustomerUseCase from "../../../usecase/customer/create/create.customer.usecase";
import ListCustomerUseCase from "../../../usecase/customer/list/list.customer.usecase";
import CustomerRepository from "../../customer/repository/sequelize/customer.repository";
import CustomerPresenter from "../presenters/customer.presenter";

export const customerRoute = express.Router();

customerRoute.post('/', async (req: Request, res: Response) => {
    const usecase = new CreateCustomerUseCase(new CustomerRepository());

    try {
        const customerDto = {
            name: req.body.name,
            address: {
                street: req.body.address.street,
                city: req.body.address.city,
                number: req.body.address.number,
                zip: req.body.address.zip,
            }
        }

        const output = await usecase.execute(customerDto);
        res.send(output);
    } catch (err) {
        res.status(500).send(err);
    }
});

customerRoute.get('/', async (req: Request, res: Response) => {
    const usecase = new ListCustomerUseCase(new CustomerRepository());

    try {
        const output = await usecase.execute({});
        //res.send(output);
        
        //O retorno do DTO != Resultado do que eu quero disponibilizar como resposta da API
        //Tudo é JSON. E se eu quiser o resultado em XML?
        //Solução usar Presenter

        res.format({
            json: async () => res.send(output),
            xml: async () => res.send(CustomerPresenter.listXML(output)),
        });
        
    } catch (err) {
        res.status(500).send(err);
    }
});