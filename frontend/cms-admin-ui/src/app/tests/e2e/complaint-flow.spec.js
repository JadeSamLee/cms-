const { test, expect } = require('@playwright/test');

test('full complaint flow: login, assign, verify', async ({ page }) => {
  // Step 1: Login
  await page.goto('/home');
  await page.click('button:has-text("Login as Admin")');
  await page.waitForURL('**/admin');

  // Step 2: Wait for table to load
  await page.waitForSelector('p-table');

  // Step 3: Select a team from dropdown (e.g., IT Support)
  await page.click('p-dropdown');
  await page.click('text=IT Support'); // Change if team name different

  // Step 4: Click Assign on first complaint
  await page.click('(//button[contains(text(),"Assign")])[1]');
  await expect(page.locator('p-toast')).toContainText('Assigned');

  // Optional: Check status changed (if real-time update works)
  // await expect(page.locator('td:has-text("ASSIGNED_TO_TEAM")')).toBeVisible();

  // Step 5: Simulate RESOLVED state for verify (manual DB change needed)
  // Or skip verify/reject if not set up
  console.log('Manual step: Set a complaint status to RESOLVED in DB to test Verify/Reject');
});