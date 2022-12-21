import { Sequelize } from "sequelize-typescript";
import Product from "../../../domain/product/entity/product";
import ProductModel from "../../../infrastructure/product/repository/sequelize/product.model";
import ProductRepository from "../../../infrastructure/product/repository/sequelize/product.repository";
import UpdateProductUseCase from "./update.product.usecase";

const product = new Product("123", "Product 1", 100.34);

const input = {
  id: product.id,
  name: "Product 1 Updated",
  price: 200.56,
}

describe("Test update product use case", () => {
  
  let sequelize: Sequelize;
  
  beforeEach(async () => {
    sequelize = new Sequelize({
      dialect: "sqlite",
      storage: ":memory:",
      logging: false,
      sync: { force: true },
    });

    await sequelize.addModels([
      ProductModel,
    ]);
    await sequelize.sync();
  });

  afterEach(async () => {
    await sequelize.close();
  });

  it("should update a product", async () => {
      const productRepository = new ProductRepository();
      
      const product = new Product("123", "Product 1", 100.34);
      await productRepository.create(product);

      const usecase = new UpdateProductUseCase(productRepository);
      
      const output = await usecase.execute(input);

      expect(output).toEqual(input);
  });


});
