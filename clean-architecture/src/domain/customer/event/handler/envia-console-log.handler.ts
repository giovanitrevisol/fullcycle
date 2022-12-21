import EventHandlerInterface from "../../../@shared/event/event-handler.interface";
import CustomerChangeAddressEvent from "../customer-change-address.event";

export default class EnviaConsoleLogHandler
  implements EventHandlerInterface<CustomerChangeAddressEvent>
{
  handle(event: CustomerChangeAddressEvent): void {
    let id = event.eventData.id;
    let name = event.eventData.name;
    let address = event.eventData.Address.toString();

    console.log(`Endereço do cliente: ${id}, ${name} alterado para: ${address}`); 
    //console.log(event.eventData);
  }
}
