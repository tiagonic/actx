import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioInserirComponent } from './usuario-inserir.component';

describe('UsuarioInserirComponent', () => {
  let component: UsuarioInserirComponent;
  let fixture: ComponentFixture<UsuarioInserirComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UsuarioInserirComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioInserirComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
