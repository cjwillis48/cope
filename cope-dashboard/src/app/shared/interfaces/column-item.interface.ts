import { NzTableSortFn } from "ng-zorro-antd/table";

export interface ColumnItem {
  title: string;
  compare: NzTableSortFn;
  priority: number;
}
