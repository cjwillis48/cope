import {
  AfterViewInit,
  Component,
  OnInit,
  QueryList,
  ViewChildren
} from '@angular/core';
import { NavigationEnd, Router } from "@angular/router";
import { filter, map } from "rxjs/operators";
import { NzMenuItemDirective } from "ng-zorro-antd/menu";
import { OktaAuthService } from '@okta/okta-angular';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  viewProviders: [{ provide: NzMenuItemDirective }]
})
export class NavbarComponent implements AfterViewInit, OnInit {

  @ViewChildren(NzMenuItemDirective)
  menuItems: QueryList<NzMenuItemDirective>;

  constructor(private router: Router) {

  }
  async ngOnInit() {
  }


  ngAfterViewInit(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      map(event => event as NavigationEnd),
      map(event => event.urlAfterRedirects || event.url))
      .subscribe(url => {
          this.menuItems.forEach(menuItem => {
            if (menuItem.listOfRouterLinkWithHref.some(value => url.startsWith(value.href))) {
              menuItem.nzSelected = true;
            }
          });
        }
      );
  }
}
