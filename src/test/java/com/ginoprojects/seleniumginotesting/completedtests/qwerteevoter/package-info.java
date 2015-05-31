/**
 * Created by GinoGalotti on 30/05/2015.
 * ginogalotti.com
 * ginogalotti@gmail.com
 *
 * This package is going to serve as a Selenium training where I'm going to use the famous dialy tee Qwertee site (qwertee.com)
 * and make a test that is going to log in the website, go to the vote page and vote for all the shirts with a given description
 * or keyword. Because I love zelda shirts and I want to vote all of them!
 *
 * Scope:
 *  .Learning to log in in a website using selenium.
 *  .Working with XPATH to deal with automatically generated pages (like the shirts to vote)
 *  .Following TDD principles, focusing on QwerteeTest class and only writing QwerteePage functions when the tests are failing.
 *
 * Lessons learned:
 *  .The web editor has created the HTML objects in a way that make them quite inaccessible. The easiest way is using XPATH with an automated tool,
 *  but this is going to fail whenever the editor change the distribution.
 *  .When you want to find by xpath inside a WebElement, you have to start the pattern with ".//" instead of "//". I have to admit that this cost me way TOO much time to figure out.
 *  .The script that determine when you've already voted a tee is way slower than the page to load. Problem is that I don't know what's that script, so I just had
 *   to force a wait.
 */
package com.ginoprojects.seleniumginotesting.completedtests.qwerteevoter;
