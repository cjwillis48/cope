import { Address } from "../../shared/interfaces/address.interface";
import { Client } from "../../shared/interfaces/client.interface";

export interface Prescription {
  patient: Patient;
  prescriber: Prescriber;
  pharmacy: Pharmacy;
  client: Client;
  drugName: string;
  quantity: number;
  dose: number;
  dosageMeasurement: string;
  instructions?: string;
  prescriptionDate: Date;
}

export interface Prescriber {
  firstName: string;
  lastName: string;
  address: Address;
}

export interface Patient {
  firstName: string;
  lastName: string;
  address: Address;
}

export interface Pharmacy {
  id: number;
  name: string;
  address: Address;
}


