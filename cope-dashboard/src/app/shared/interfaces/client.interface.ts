import {Address} from "./address.interface";

export interface Client {
  clientId: string;
  softwareTitle: string;
  localizationName: string;
  locality: string;
  contactAddress: Address;
  contactPhone: string;
}
