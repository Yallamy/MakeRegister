import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PessoaAddComponent } from './pessoa/pessoa-add/pessoa-add.component';
import { PessoaEditComponent } from './pessoa/pessoa-edit/pessoa-edit.component';
import { PessoaDetailComponent } from './pessoa/pessoa-detail/pessoa-detail.component';
import { PessoaListComponent } from './pessoa/pessoa-list/pessoa-list.component';

const routes: Routes = [
  {
    path: 'pessoa-add',
    component: PessoaAddComponent,
    data: { title: 'Adicionar Pessoa' }
  },
  {
    path: 'pessoa-edit/:id',
    component: PessoaEditComponent,
    data: { title: 'Editar Pessoa' }
  },
  {
    path: 'pessoa-details/:id',
    component: PessoaDetailComponent,
    data: { title: 'Detalhar Pessoa' }
  },
  {
    path: 'pessoas',
    component: PessoaListComponent,
    data: { title: 'Listar Pessoas' }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
