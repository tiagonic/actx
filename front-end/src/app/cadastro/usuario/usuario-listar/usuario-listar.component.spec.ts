import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioListarComponent } from './usuario-listar.component';

describe('UsuarioListarComponent', () => {
  let component: UsuarioListarComponent;
  let fixture: ComponentFixture<UsuarioListarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UsuarioListarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuarioListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
