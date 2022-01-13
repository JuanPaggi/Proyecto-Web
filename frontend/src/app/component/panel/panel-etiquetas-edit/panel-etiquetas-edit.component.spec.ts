import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelEtiquetasEditComponent } from './panel-etiquetas-edit.component';

describe('PanelEtiquetasEditComponent', () => {
  let component: PanelEtiquetasEditComponent;
  let fixture: ComponentFixture<PanelEtiquetasEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanelEtiquetasEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelEtiquetasEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
