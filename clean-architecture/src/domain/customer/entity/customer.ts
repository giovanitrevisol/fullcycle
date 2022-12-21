import Address from "../value-object/address";
import EventDispatcher from "../../@shared/event/event-dispatcher";
import CustomerChangeAddressEvent from "../../customer/event/customer-change-address.event";
import EnviaConsoleLogHandler from "../../customer/event/handler/envia-console-log.handler";
import Entity from "../../@shared/entity/entity.abstract";
import NotificationError from "../../@shared/notification/notification.error";
import CustomerValidatorFactory from "../factory/customer.validator.factory";

export default class Customer extends Entity {
  //private _id: string;
  private _name: string = "";
  private _address!: Address;
  private _active: boolean = false;
  private _rewardPoints: number = 0;

  constructor(id: string, name: string) {
    super();
    this._id = id;
    this._name = name;
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

  get rewardPoints(): number {
    return this._rewardPoints;
  }

  validate() {
    CustomerValidatorFactory.create().validate(this);

    /*
    if (this.id.length === 0) {
      //throw new Error("Id is required");
      this.notification.addError({
        context: "customer",
        message: "Id is required",
      });
    }
    if (this._name.length === 0) {
      //throw new Error("Name is required");
      this.notification.addError({
        context: "customer",
        message: "Name is required",
      });
    }
    */
  }

  changeName(name: string) {
    this._name = name;
    this.validate();
  }

  get Address(): Address {
    return this._address;
  }
  
  changeAddress(address: Address) {
    const eventDispatcher = new EventDispatcher();
    const eventLogHandler = new EnviaConsoleLogHandler();    
    eventDispatcher.register("CustomerChangeAddressEvent", eventLogHandler);
    
    const customerChangeAddressEvent = new CustomerChangeAddressEvent(this);

    this._address = address;

    eventDispatcher.notify(customerChangeAddressEvent);
  }

  isActive(): boolean {
    return this._active;
  }

  activate() {
    if (this._address === undefined) {
      throw new Error("Address is mandatory to activate a customer");
    }
    this._active = true;
  }

  deactivate() {
    this._active = false;
  }

  addRewardPoints(points: number) {
    this._rewardPoints += points;
  }

  set Address(address: Address) {
    this._address = address;
  }
}
