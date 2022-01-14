import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelGaleriasEditComponent } from './panel-galerias-edit.component';

describe('PanelGaleriasEditComponent', () => {
  let component: PanelGaleriasEditComponent;
  let fixture: ComponentFixture<PanelGaleriasEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanelGaleriasEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelGaleriasEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
