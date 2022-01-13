import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelPublicacionesCrearComponent } from './panel-publicaciones-crear.component';

describe('PanelPublicacionesCrearComponent', () => {
  let component: PanelPublicacionesCrearComponent;
  let fixture: ComponentFixture<PanelPublicacionesCrearComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanelPublicacionesCrearComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelPublicacionesCrearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
