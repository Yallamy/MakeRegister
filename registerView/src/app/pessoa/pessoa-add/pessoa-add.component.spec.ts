import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PessoaAddComponent } from './pessoa-add.component';

describe('PessoaAddComponent', () => {
  let component: PessoaAddComponent;
  let fixture: ComponentFixture<PessoaAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PessoaAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PessoaAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
