import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InsertComponent } from './insert/insert.component';
import { UpdateComponent } from './update/update.component';
import { SelectComponent } from './select/select.component';
import { DeleteComponent } from './delete/delete.component';
import { SharedComponent } from './shared/shared.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HomeComponent } from './home/home.component';
import { FilterComponent } from './shared/filter/filter.component';
import { MedicComponent } from './medic/medic.component';
import { HttpClientModule } from '@angular/common/http';

import {FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    InsertComponent,
    UpdateComponent,
    SelectComponent,
    DeleteComponent,
    SharedComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    FilterComponent,
    MedicComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    
  ],
  providers: [FilterComponent, SelectComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
