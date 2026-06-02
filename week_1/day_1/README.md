I completed the git branching exercise.

I first added a README.md file to the main branch. Then I created a new feature branch and added two new files describing a test plan, which was later merged to the main branch. Then I added an update branch and made changes to the scope in the test-plan.md file and intentionally made changes to that same file in the main branch that conflicted with them. I accepted both changes to resolve the conflict.

At the end, my git log looked like this:
```
*   f70da5f (HEAD -> main) merge test plan feature w main
|\  
| * 464b4a0 (feature/update-test-plan) Expand test plan scope on feature branch
* | 5dcff53 Update test plan scope on main
|/  
*   511f989 merge test plan to main
|\  
| * e047bc5 Add team section to README
* | bfec355 Add initial test cases
* | ed4b822 Add test plan document
|/  
* 6f72332 Initial commit: added README
```

I also started the functions exercise on this day. The actual exercise is included in the day 2 folder, as it was completed then.