import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelPublicacionesEditarComponent } from './panel-publicaciones-editar.component';

describe('PanelPublicacionesEditarComponent', () => {
  let component: PanelPublicacionesEditarComponent;
  let fixture: ComponentFixture<PanelPublicacionesEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanelPublicacionesEditarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelPublicacionesEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
