import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css',
    ]
})
export class MenuComponent implements OnInit {

    constructor() { }

    items: MenuItem[];
    route: String;

    ngOnInit() {
        this.items = [{
            label: 'Menu',
            items: [
                { label: 'Session', icon: 'fa-briefcase', routerLink: ['agents'] }

            ]
        },
        {
            label: 'Listes',
            items: [
                { label: 'Agents', icon: 'fa-table', routerLink: ['agents'] },
                { label: 'Flux', icon: 'fa-table', routerLink: ['flux'] },
                { label: 'Grades', icon: 'fa-table' },
                { label: 'Formateurs', icon: 'fa-table' },
                { label: 'Formations', icon: 'fa-table' },
                { label: 'Habilitations', icon: 'fa-table' },
                { label: 'Salles', icon: 'fa-table' }
            ]
        },
        {
            label: 'Gestion',
            items: [
                { label: 'Statistiques', icon: 'fa-sort-numeric-desc' },
                { label: 'Graphiques', icon: 'fas fa-area-chart' },
            ]
        }];
    }
}
