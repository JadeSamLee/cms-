const { test, expect } = require('@playwright/test');

test('should login as admin and redirect to dashboard', async ({ page }) => {
  await page.goto('/home');

  // Click login button
  await page.click('button:has-text("Login as Admin")');

  // Wait for navigation to /admin
  await page.waitForURL('**/admin');

  // Check dashboard header
  await expect(page.locator('h1')).toHaveText('Admin Dashboard');
  await expect(page.locator('text=Complaint Tracking Dashboard')).toBeVisible();
});