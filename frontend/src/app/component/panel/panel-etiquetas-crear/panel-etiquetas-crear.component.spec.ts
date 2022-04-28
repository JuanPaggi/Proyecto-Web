import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelEtiquetasCrearComponent } from './panel-etiquetas-crear.component';

describe('PanelEtiquetasCrearComponent', () => {
  let component: PanelEtiquetasCrearComponent;
  let fixture: ComponentFixture<PanelEtiquetasCrearComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanelEtiquetasCrearComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelEtiquetasCrearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
