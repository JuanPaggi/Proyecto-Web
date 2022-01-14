import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelGaleriasCrearComponent } from './panel-galerias-crear.component';

describe('PanelGaleriasCrearComponent', () => {
  let component: PanelGaleriasCrearComponent;
  let fixture: ComponentFixture<PanelGaleriasCrearComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanelGaleriasCrearComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelGaleriasCrearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
