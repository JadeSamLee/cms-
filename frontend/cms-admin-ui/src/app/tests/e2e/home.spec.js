const { test, expect } = require('@playwright/test');

test('should load home page and show login button', async ({ page }) => {
  await page.goto('/home');

  await expect(page.locator('h1')).toHaveText('Complaint Management System');
  await expect(page.locator('text=Login as Admin')).toBeVisible();
});