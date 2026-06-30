# MediChart Analytics - Development Plan

**Phase A:** Pilots (9-12 months)
Regulatory Agile would be more suitable for Phase A for the following reasons:
- **The time frame for this phase is short, and Agile is a faster, more efficient process.** Depending on the time that the phase takes within the given range and the length of each Agile iteration, there will be enough time for 3-6 iterations of the product. Were this a waterfall SDLC, the product may not be testable until 7-9 months into development, which is unaccpetable given this time frame.
- **Agile's flexibility is required to facilitate frequent change.**  In order to apply frequent changes to the UX, metrics, and drill-downs, a more flexible development process is necessary. Agile enables faster feedback loops and allows the uncertainty that might be present in the first phase of development.
- **Given the sensitive nature of the product, compliance and documentation should be maintained from day one.** Regulated Agile will provide the flexibility of standard Agile, but should involve phase-appropriate documentation for each iteration of development.
- **The team has a cross-functional skill mix.** An Agile development strategy will make use of every team member's individual skill set. No part of the project will be completely dependent on another part, meaning that the team members can work on their own parts of the project without having to wait for other team members to finish their parts.

| Risk | Mitigation |
| --- | --- |
| Lighter documentation - might not be sufficient for the regulated nature of the product. | Phase-equivalent documentation at each milestone. |

**Phase B:** Scaled rollout (18 months)
A Waterfall software lifecycle would be more appropriate for this phase for the following reasons:
- **A Waterfall lifecycle produces a strong document trail.** For software used in medical devices, strong documentation is required. The regulators will need this documentation as verification evidence before the product is rolled out.
- **The rollout is under a fixed-price and fixed-time contract.** Agile is less suitable for a project that has a set price and time frame, as the flexibility can lead to unpredictable times and/or costs. An Agile lifecycle would have the risk of taking longer than expected, which is acceptable in Phase A but not when the scope of the project has already been fixed.

| Risk | Mitigation |
| --- | --- |
| Testing will be more difficult and will not take place until a relatively late stage of development. | The longer time frame alloted for Phase B will allow for more time to test.

## Testing Philosophy
During Phase A, the whole development team will be accountable for testing. The three developers will write unit tests. The two testers will expand upon the tests that the developers write and will be able to apply more of their time to edge cases and integration tests. The DevOps will organize the testing environment and take accountability over test automation. The designer and product lead should both have some understanding of what the finished product (at least, at product end of the current iteration) should look like and design tests accordingly based off of user stories. The short iterations of the product will allow frequent left shifting to catch defects as early as possible.
During Phase B, the testing philosophy will become more rigid and the two testers will begin to own the product's quality.