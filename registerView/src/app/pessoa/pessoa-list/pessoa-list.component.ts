import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { Pessoa } from '../classes/pessoa';
import { PagePessoa } from '../classes/pagePessoa';
  
@Component({
  selector: 'app-pessoa-list',
  templateUrl: './pessoa-list.component.html',
  styleUrls: ['./pessoa-list.component.scss']
})
export class PessoaListComponent implements OnInit {
	
  displayedColumns: string[] = ['nome', 'cpf'];
  data: Pessoa[] = [];
  isLoadingResults = true;

  constructor(private api: ApiService) { }

  ngOnInit(): void {
	  
	this.api.getPessoas()
	.subscribe(res => {
      this.data = res.content;
      console.log(this.data);
      this.isLoadingResults = false;
    }, err => {
      console.log(err);
      this.isLoadingResults = false;
    });
  }

}
