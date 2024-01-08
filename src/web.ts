import { WebPlugin } from '@capacitor/core';

import type { SafeAreaInset, SafeAreaPlugin } from './definitions';

export class SafeAreaWeb extends WebPlugin implements SafeAreaPlugin {
  async getSafeArea(): Promise<SafeAreaInset> {
    return { top: 0, bottom: 0, left: 0, right: 0 };
  }

  async getStatusBarHeight(): Promise<{ height: number }> {
    return { height: 0 };
  }
}
