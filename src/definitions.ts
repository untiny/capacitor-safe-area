export interface SafeAreaPlugin {
  /**
   * 获取安全区域
   */
  getSafeArea(): Promise<SafeAreaInset>;
  /**
   * 获取状态栏高度
   */
  getStatusBarHeight(): Promise<{ height: number }>;
}

export interface SafeAreaInset {
  top: number;
  right: number;
  bottom: number;
  left: number;
}
