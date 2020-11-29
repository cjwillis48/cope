import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-acknowledgements',
  templateUrl: './acknowledgements.component.html',
  styleUrls: ['./acknowledgements.component.css']
})
export class AcknowledgementsComponent implements OnInit {
  sampleAcknowledgement: any = {
    uuid: 'string',
    input: [
      {
        'field': 'string',
        'value': 'JSON string of provided input value'
      }
    ],
    operation: 'string',
    output: 'JSON string of resulting value',
    startTime: 'yyyy-MM-ddTHH:mm:ss.SSS',
    completeTime: 'yyyy-MM-ddTHH:mm:ss.SSS',
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
    }
  }


  constructor() {
  }

  ngOnInit(): void {
  }

  getAcknowledgementSample() {
    return JSON.stringify(this.sampleAcknowledgement, null, 2);
  }
}
