
export interface FeatureToggle {
  id?: string;
  technicalName: string;
  displayName: string;
  expiresOn: string;
  description: string;
  inverted: boolean;
  customerIds: string[];
}

export interface FeatureToggleListItem {
  id?: string;
  technicalName: string;
  displayName?: string;
}
