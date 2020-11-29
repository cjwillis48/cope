import {Client} from "../../shared/interfaces/client.interface";

export interface Acknowledgement {
  uuid: string;
  operation: string;
  success: boolean,
  input: Parameter[];
  output: string;
  client: Client;
  startTime: Date;
  completeTime: Date;
}

export interface Parameter {
  field: string;
  value: any;
}
