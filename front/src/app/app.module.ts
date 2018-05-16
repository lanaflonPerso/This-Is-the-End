import { GradeService } from './grade.service';
import { IlotService } from './ilot.service';
import { FluxService } from './flux.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AgentService } from './agent.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AccordionModule } from 'primeng/accordion';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { MegaMenuModule } from 'primeng/megamenu';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { MenuModule } from 'primeng/menu';
import { MenuItem } from 'primeng/api';
import { TableauAgentComponent } from './tableau-agent/tableau-agent.component';
import { DialogModule, KeyFilterModule, TooltipModule } from 'primeng/primeng';
import { TableModule } from 'primeng/table';
import { FormsModule, FormGroupDirective, FormControlDirective, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { FlexLayoutModule } from '@angular/flex-layout';
import { InputMaskModule } from 'primeng/inputmask';
import { MessageService } from 'primeng/components/common/messageservice';
import { GrowlModule, MessageModule } from 'primeng/primeng';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './/app-routing.module';
import { DropdownModule } from 'primeng/dropdown';
import { TableauFluxComponent } from './tableau-flux/flux.component';
import { CodeHighlighterModule } from 'primeng/codehighlighter';
import { TableauIlotComponent } from './tableau-ilot/tableau-ilot.component';
import { TableauGradeComponent } from './tableau-grade/tableau-grade.component';
import { TableauFormationComponent } from './tableau-formation/tableau-formation.component';
import { TableauSalleComponent } from './tableau-salle/tableau-salle.component';
import { TableauFormateurComponent } from './tableau-formateur/tableau-formateur.component';




@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    TableauAgentComponent,
    TableauFluxComponent,
    TableauIlotComponent,
    TableauGradeComponent,
    TableauFormationComponent,
    TableauSalleComponent,
    TableauFormateurComponent
  ],
  imports: [
    BrowserModule,
    AccordionModule,
    AngularFontAwesomeModule,
    MegaMenuModule,
    MenuModule,
    DialogModule,
    TableModule,
    FormsModule,
    HttpClientModule,
    ConfirmDialogModule,
    FlexLayoutModule,
    BrowserModule,
    ReactiveFormsModule,
    InputMaskModule,
    KeyFilterModule,
    GrowlModule,
    MessageModule,
    BrowserAnimationsModule,
    RouterModule,
    AppRoutingModule,
    DropdownModule,
    CodeHighlighterModule,
    TooltipModule

  ],
  exports: [BrowserModule],
  providers: [AgentService, FluxService, IlotService, GradeService, ConfirmationService,
    FormControlDirective, FormGroupDirective, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
