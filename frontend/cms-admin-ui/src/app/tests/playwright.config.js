/** @type {import('@playwright/test').PlaywrightTestConfig} */
const config = {
  testDir: './e2e/tests',
  timeout: 30000,
  use: {
    baseURL: 'http://localhost:4200',
    headless: true,
    viewport: { width: 1280, height: 720 },
    actionTimeout: 10000,
    ignoreHTTPSErrors: true,
    video: 'on-first-retry',
  },
  projects: [
    {
      name: 'chromium',
      use: { browserName: 'chromium' },
    },
    {
      name: 'firefox',
      use: { browserName: 'firefox' },
    },
    {
      name: 'webkit',
      use: { browserName: 'webkit' },
    },
  ],
};

module.exports = config;
// {
//   "name": "cms-frontend",
//   "scripts": {
//     "e2e": "playwright test",
//     "e2e:ui": "playwright test --ui",
//     "e2e:debug": "playwright test --headed --browser=chromium"
//   },
//   "devDependencies": {
//     "@playwright/test": "^1.48.0"
//   }
// }