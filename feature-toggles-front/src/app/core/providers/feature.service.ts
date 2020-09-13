import { Injectable } from '@angular/core';
import { BaseUrls } from 'src/app/model/base-urls.model';
import { HttpClient } from '@angular/common/http';
import { FeatureToggle, FeatureToggleListItem } from 'src/app/model/feature-toggle.model';

const featureUrls = new BaseUrls('features');

@Injectable({
  providedIn: 'root'
})
export class FeatureService {

  constructor(private http: HttpClient) { }

  create(feature: FeatureToggle) {
    return this.http.post<FeatureToggle>(featureUrls.root, feature);
  }

  list() {
    return this.http.get<FeatureToggleListItem[]>(featureUrls.root);
  }

  update(id: string, feature: FeatureToggle) {
    return this.http.put<FeatureToggle>(featureUrls.byId(id), feature);
  }

  getById(id: string) {
    return this.http.get<FeatureToggle>(featureUrls.byId(id));
  }

  archive(name: string) {
    return this.http.delete(featureUrls.byId(name));
  }
}
