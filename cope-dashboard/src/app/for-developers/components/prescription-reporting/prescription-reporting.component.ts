import { Component, OnInit } from '@angular/core';
import { Prescription } from "../../../prescription/interfaces/prescription.interface";

@Component({
  selector: 'app-prescription-reporting',
  templateUrl: './prescription-reporting.component.html',
  styleUrls: ['./prescription-reporting.component.css']
})
export class PrescriptionReportingComponent implements OnInit {
  samplePrescription: any = {
    patient: {
      ssn: 'string',
      firstName: 'string',
      lastName: 'string',
      address: {
        street: 'string',
        city: 'string',
        state: 'valid state name in ALL CAPS',
        zipCode: 'string'
      }
    },
    prescriber: {
      ssn: 'string',
      firstName: 'string',
      lastName: 'string',
      address: {
        street: 'string',
        city: 'string',
        state: 'valid state name in ALL CAPS',
        zipCode: 'string'
      }
    },
    pharmacy: {
      name: 'string',
      address: {
        street: 'string',
        city: 'string',
        state: 'valid state name in ALL CAPS',
        zipCode: 'string'
      }
    },
    client: {
      clientId: 'string',
      softwareTitle: 'string',
      localizationName: 'string',
      locality: "one of ['STATE', 'COUNTY', 'CITY', 'UNKNOWN']",
      contactAddress: {
        street: 'string',
        city: 'string',
        state: 'valid state name in ALL CAPS',
        zipCode: 'string'
      },
      contactPhone: 'string'
    },
    drugName: 'string',
    quantity: 'positive integer',
    dose: 'positive floating point number',
    dosageMeasurement: "one of ['MILLIGRAM', 'MICROGRAM']",
    instructions: 'string',
    prescriptionDate: 'yyyy-MM-dd'
  }

  constructor() {
  }

  ngOnInit(): void {
  }

  getPrescriptionSample(): string {
    return JSON.stringify(this.samplePrescription, null, 2);
  }

}
