import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ApiService } from '../api.service';
import { Pessoa } from '../classes/pessoa';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-pessoa-edit',
  templateUrl: './pessoa-edit.component.html',
  styleUrls: ['./pessoa-edit.component.scss']
})
export class PessoaEditComponent implements OnInit {
	
  pessoaForm: FormGroup;
  id:number=null;
  nome:string='';
  sexo:string='';
  email:string='';
  dtNascimento:Date=null;
  naturalidade:string='';
  nacionalidade:string='';
  cpf:string='';
  isLoadingResults = false;

  constructor(private router: Router, private route: ActivatedRoute, private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
	  
	this.getPessoa(this.route.snapshot.params['id']);
	this.pessoaForm = this.formBuilder.group({
		'nome' : [null, Validators.required],
		'sexo' : [null, Validators.nullValidator],
		'email' : [null, Validators.email],
		'dtNascimento' : [null, Validators.required],
		'naturalidade' : [null, Validators.max(100)],
		'nacionalidade' : [null, Validators.max(100)],
		'cpf' : [null, Validators.required]
	});
  }
  
  getPessoa(id) {
	this.api.getPessoa(id).subscribe(data => {
		this.id = data.id;
		this.pessoaForm.setValue({
		  nome: data.nome,
		  sexo: data.sexo,
		  email: data.email,
		  dtNascimento: data.dtNascimento,
		  naturalidade: data.naturalidade,
		  nacionalidade: data.nacionalidade,
		  cpf: data.cpf
		});
	});
  }
  
  onFormSubmit(form:NgForm) {
	  this.isLoadingResults = true;
	  this.api.updatePessoa(this.id, form)
		.subscribe(res => {
			let id = res['id'];
			this.isLoadingResults = false;
			this.router.navigate(['/pessoa-details', id]);
		  }, (err) => {
			console.log(err);
			this.isLoadingResults = false;
		  }
		);
   }
   
  pessoaDetails() {
	this.router.navigate(['/pessoa-details', this.id]);
  }

}
