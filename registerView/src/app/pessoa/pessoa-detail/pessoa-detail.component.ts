import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../api.service';
import { Pessoa } from '../classes/pessoa';

@Component({
  selector: 'app-pessoa-detail',
  templateUrl: './pessoa-detail.component.html',
  styleUrls: ['./pessoa-detail.component.scss']
})
export class PessoaDetailComponent implements OnInit {
	
  pessoa: Pessoa = { id: null, nome: '', sexo: '', email: '', dtNascimento: null, naturalidade: '', nacionalidade: '', cpf: '' };
  isLoadingResults = true;	

  constructor(private route: ActivatedRoute, private api: ApiService, private router: Router) { }

  ngOnInit(): void {
	  this.getPessoaDetails(this.route.snapshot.params['id']);
  }
  
  getPessoaDetails(id) {
	  this.api.getPessoa(id)
		.subscribe(data => {
		  this.pessoa = data;
		  console.log(this.pessoa);
		  this.isLoadingResults = false;
		});
  }
  
  deletePessoa(id) {
	  this.isLoadingResults = true;
	  this.api.deletePessoa(id)
		.subscribe(res => {
			this.isLoadingResults = false;
			this.router.navigate(['/pessoas']);
		  }, (err) => {
			console.log(err);
			this.isLoadingResults = false;
		  }
		);
   }

}
