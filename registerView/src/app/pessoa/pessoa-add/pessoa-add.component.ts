import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../api.service';
import { Pessoa } from '../classes/pessoa';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-pessoa-add',
  templateUrl: './pessoa-add.component.html',
  styleUrls: ['./pessoa-add.component.scss']
})
export class PessoaAddComponent implements OnInit {
	
  pessoaForm: FormGroup;
  nome:string='';
  sexo:string='';
  email:string='';
  dtNascimento:Date=null;
  naturalidade:string='';
  nacionalidade:string='';
  cpf:string='';
  isLoadingResults = false;

  constructor(private router: Router, private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
	  
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
  
  onFormSubmit(form:NgForm) {
	this.isLoadingResults = true;
	this.api.addPessoa(form)
    .subscribe(res => {
        let id = res['id'];
        this.isLoadingResults = false;
        this.router.navigate(['/pessoa-details', id]);
      }, (err) => {
        console.log(err);
        this.isLoadingResults = false;
      });
  }

}
