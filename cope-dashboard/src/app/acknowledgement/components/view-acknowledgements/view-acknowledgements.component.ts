import { Component, OnInit } from '@angular/core';
import { ColumnItem } from "../../../shared/interfaces/column-item.interface";

@Component({
  selector: 'app-complications',
  templateUrl: './view-acknowledgements.component.html',
  styleUrls: ['./view-acknowledgements.component.css']
})
export class ViewAcknowledgementsComponent implements OnInit {

  columns: ColumnItem[] = [
    {
      name: 'Name',
      sortOrder: null,
      sortFn: (a: DataItem, b: DataItem) => a.name.localeCompare(b.name),
      sortDirections: ['ascend', 'descend', null],
      filterMultiple: true,
      listOfFilter: [
        { text: 'Joe', value: 'Joe' },
        { text: 'Jim', value: 'Jim', byDefault: true }
      ],
      filterFn: (list: string[], item: DataItem) => list.some(name => item.name.indexOf(name) !== -1)

    }
  ];

  data: any;

  constructor() {
  }

  ngOnInit(): void {
  }

}
