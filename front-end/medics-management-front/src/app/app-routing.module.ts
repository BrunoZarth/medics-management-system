import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DeleteComponent } from './delete/delete.component';
import { InsertComponent } from './insert/insert.component';
import { SelectComponent } from './select/select.component';
import { UpdateComponent } from './update/update.component';

const routes: Routes = [
  {path: "insert", component: InsertComponent},
  {path: "delete", component: DeleteComponent},
  {path: "select", component: SelectComponent},
  {path: "update", component: UpdateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
