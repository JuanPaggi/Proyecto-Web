import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelEtiquetasComponent } from './panel-etiquetas.component';

describe('PanelEtiquetasComponent', () => {
  let component: PanelEtiquetasComponent;
  let fixture: ComponentFixture<PanelEtiquetasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanelEtiquetasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelEtiquetasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
