import SendEmailWhenProductIsCreatedHandler from "../../product/event/handler/send-email-when-product-is-created.handler";
import ProductCreatedEvent from "../../product/event/product-created.event";
import EventDispatcher from "./event-dispatcher";
import CustomerCreatedEvent from "../../customer/event/customer-created.event";
//import CustomerChangeAddressEvent from "../../customer/event/customer-change-address.event";
import EnviaConsoleLog1Handler from "../../customer/event/handler/envia-console-log1.handler";
import EnviaConsoleLog2Handler from "../../customer/event/handler/envia-console-log2.handler";
import EnviaConsoleLogHandler from "../../customer/event/handler/envia-console-log.handler";
import Customer from "../../customer/entity/customer";
import Address from "../../customer/value-object/address";

describe("Domain events tests", () => {
  let eventDispatcher: EventDispatcher;
  let eventHandler: SendEmailWhenProductIsCreatedHandler;
  let eventLog1Handler: EnviaConsoleLog1Handler;
  let eventLog2Handler: EnviaConsoleLog2Handler;
  let eventLogHandler: EnviaConsoleLogHandler;

  beforeEach(async () => {
    eventDispatcher = new EventDispatcher();
    eventHandler = new SendEmailWhenProductIsCreatedHandler();
    eventDispatcher.register("ProductCreatedEvent", eventHandler);

    eventLog1Handler = new EnviaConsoleLog1Handler();
    eventLog2Handler = new EnviaConsoleLog2Handler();
    eventDispatcher.register("CustomerCreatedEvent", eventLog1Handler);
    eventDispatcher.register("CustomerCreatedEvent", eventLog2Handler);

    eventLogHandler = new EnviaConsoleLogHandler();
    eventDispatcher.register("CustomerChangeAddressEvent", eventLogHandler);
  });

  it("should register an event handler", () => {

    expect(
      eventDispatcher.getEventHandlers["ProductCreatedEvent"]
    ).toBeDefined();
    expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"].length).toBe(
      1
    );
    expect(
      eventDispatcher.getEventHandlers["ProductCreatedEvent"][0]
    ).toMatchObject(eventHandler);

    //Log1 e Log2
    expect(
      eventDispatcher.getEventHandlers["CustomerCreatedEvent"]
    ).toBeDefined();
    expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"].length).toBe(
      2
    );
    expect(
      eventDispatcher.getEventHandlers["CustomerCreatedEvent"][0]
    ).toMatchObject(eventLog1Handler);
    expect(
      eventDispatcher.getEventHandlers["CustomerCreatedEvent"][1]
    ).toMatchObject(eventLog2Handler);

    //ChangeAddress
    expect(
      eventDispatcher.getEventHandlers["CustomerChangeAddressEvent"]
    ).toBeDefined();
    expect(eventDispatcher.getEventHandlers["CustomerChangeAddressEvent"].length).toBe(
      1
    );
    expect(
      eventDispatcher.getEventHandlers["CustomerChangeAddressEvent"][0]
    ).toMatchObject(eventLogHandler);

  });

  it("should unregister an event handler", () => {

    expect(
      eventDispatcher.getEventHandlers["ProductCreatedEvent"][0]
    ).toMatchObject(eventHandler);

    eventDispatcher.unregister("ProductCreatedEvent", eventHandler);

    expect(
      eventDispatcher.getEventHandlers["ProductCreatedEvent"]
    ).toBeDefined();
    expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"].length).toBe(
      0
    );

    //Log1 e Log2
    expect(
      eventDispatcher.getEventHandlers["CustomerCreatedEvent"][0]
    ).toMatchObject(eventLog1Handler);
    expect(
      eventDispatcher.getEventHandlers["CustomerCreatedEvent"][1]
    ).toMatchObject(eventLog2Handler);

    eventDispatcher.unregister("CustomerCreatedEvent", eventLog1Handler);
    eventDispatcher.unregister("CustomerCreatedEvent", eventLog2Handler);

    expect(
      eventDispatcher.getEventHandlers["CustomerCreatedEvent"]
    ).toBeDefined();
    expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"].length).toBe(
      0
    );

    //ChangeAddress
    expect(
      eventDispatcher.getEventHandlers["CustomerChangeAddressEvent"][0]
    ).toMatchObject(eventLogHandler);

    eventDispatcher.unregister("CustomerChangeAddressEvent", eventLogHandler);

    expect(
      eventDispatcher.getEventHandlers["CustomerChangeAddressEvent"]
    ).toBeDefined();
    expect(eventDispatcher.getEventHandlers["CustomerChangeAddressEvent"].length).toBe(
      0
    );

  });

  it("should unregister all event handlers", () => {

    expect(
      eventDispatcher.getEventHandlers["ProductCreatedEvent"][0]
    ).toMatchObject(eventHandler);
    expect(
      eventDispatcher.getEventHandlers["CustomerCreatedEvent"][0]
    ).toMatchObject(eventLog1Handler);
    expect(
      eventDispatcher.getEventHandlers["CustomerCreatedEvent"][1]
    ).toMatchObject(eventLog2Handler);
    expect(
      eventDispatcher.getEventHandlers["CustomerChangeAddressEvent"][0]
    ).toMatchObject(eventLogHandler);

    eventDispatcher.unregisterAll();

    expect(
      eventDispatcher.getEventHandlers["ProductCreatedEvent"]
    ).toBeUndefined();
    expect(
      eventDispatcher.getEventHandlers["CustomerCreatedEvent"]
    ).toBeUndefined();
    expect(
      eventDispatcher.getEventHandlers["CustomerChangeAddressEvent"]
    ).toBeUndefined();


  });

  it("should notify all event handlers", () => {
    const spyEventHandler = jest.spyOn(eventHandler, "handle");

    const spyEventLog1Handler = jest.spyOn(eventLog1Handler, "handle");
    const spyEventLog2Handler = jest.spyOn(eventLog2Handler, "handle");

    expect(
      eventDispatcher.getEventHandlers["ProductCreatedEvent"][0]
    ).toMatchObject(eventHandler);

    const productCreatedEvent = new ProductCreatedEvent({
      name: "Product 1",
      description: "Product 1 description",
      price: 10.0,
    });

    // Quando o notify for executado o SendEmailWhenProductIsCreatedHandler.handle() deve ser chamado
    eventDispatcher.notify(productCreatedEvent);

    expect(spyEventHandler).toHaveBeenCalled();

    //Log1 e Log2    
    expect(
      eventDispatcher.getEventHandlers["CustomerCreatedEvent"][0]
    ).toMatchObject(eventLog1Handler);
    expect(
      eventDispatcher.getEventHandlers["CustomerCreatedEvent"][1]
    ).toMatchObject(eventLog2Handler);

    const customerCreatedEvent = new CustomerCreatedEvent({
      name: "Customer 1",
      address: "Customer 1 address",
    });

    eventDispatcher.notify(customerCreatedEvent);

    expect(spyEventLog1Handler).toHaveBeenCalled();
    expect(spyEventLog2Handler).toHaveBeenCalled();

    //ChangeAddress
    expect(
      eventDispatcher.getEventHandlers["CustomerChangeAddressEvent"][0]
    ).toMatchObject(eventLogHandler);

    let customer = new Customer("1", "Customer 1");
    let address = new Address("Street 1", 123, "13330-250", "São Paulo");

    customer.changeAddress(address);

    expect(customer.Address.street).toBe("Street 1");
    expect(customer.Address.number).toBe(123);
    expect(customer.Address.zip).toBe("13330-250");
    expect(customer.Address.city).toBe("São Paulo");

  });
});
