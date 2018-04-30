import { TableauFluxComponent } from './tableau-flux/flux.component';
import { TableauAgentComponent } from './tableau-agent/tableau-agent.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'agents', component: TableauAgentComponent },
  { path: 'flux', component: TableauFluxComponent }

];

@NgModule({
  imports: [CommonModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
