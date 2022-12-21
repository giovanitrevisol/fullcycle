import Customer from "../../../domain/customer/entity/customer";
import Address from "../../../domain/customer/value-object/address";
import CreateCustomerUseCase from "./create.customer.usecase";

const input = {
  name: "John",
  address: {
      street: "Street",
      number: 123,
      zip: "Zip",
      city: "City",      
  }
}

const MockRepository = () => {
  return {
    find: jest.fn(),
    findAll: jest.fn(),
    create: jest.fn(),
    update: jest.fn(),
  }
}

describe("Unit Test create customer use case", () => {
  
    it("should create a customer", async () => {
        const customerRepository = MockRepository();
        const usecase = new CreateCustomerUseCase(customerRepository);

        const output = await usecase.execute(input);

        expect(output).toEqual({
            id: expect.any(String),
            name: input.name,
            address: {
                street: input.address.street,                
                number: input.address.number,
                zip: input.address.zip,
                city: input.address.city,
            }
        });
    });

    it("should throw an error when name is missing", async () => {

      const customerRepository = MockRepository();
      const usecase = new CreateCustomerUseCase(customerRepository);

      input.name = "";

      await expect(usecase.execute(input)).rejects.toThrow(
        "Name is required"
      );
    });

    it("should throw an error when street is missing", async () => {

      const customerRepository = MockRepository();
      const usecase = new CreateCustomerUseCase(customerRepository);

      input.address.street = "";

      await expect(usecase.execute(input)).rejects.toThrow(
        "Street is required"
      );
    });

});
