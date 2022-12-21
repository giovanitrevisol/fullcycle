import ProductInterface from "./product.interface";
import Entity from "../../@shared/entity/entity.abstract";
import NotificationError from "../../@shared/notification/notification.error";

export default class ProductB extends Entity implements ProductInterface {
  //private _id: string;
  private _name: string;
  private _price: number;

  constructor(id: string, name: string, price: number) {
    super();
    this._id = id;
    this._name = name;
    this._price = price;
    this.validate();

    if (this.notification.hasErrors()) {
      throw new NotificationError(this.notification.getErrors());
    }
  }

  /*
  get id(): string {
    return this._id;
  }
  */

  get name(): string {
    return this._name;
  }

  get price(): number {
    return this._price * 2;
  }

  changeName(name: string): void {
    this._name = name;
    this.validate();
  }

  changePrice(price: number): void {
    this._price = price;
    this.validate();
  }

  validate(): boolean {
    if (this.id.length === 0) {
      //throw new Error("Id is required");
      this.notification.addError({
        context: "product",
        message: "Id is required",
      });
    }
    if (this._name.length === 0) {
      //throw new Error("Name is required");
      this.notification.addError({
        context: "product",
        message: "Name is required",
      });
    }
    if (this._price < 0) {
      //throw new Error("Price must be greater than zero");
      this.notification.addError({
        context: "product",
        message: "Price must be greater than zero",
      });
    }
    return true;
  }
}
