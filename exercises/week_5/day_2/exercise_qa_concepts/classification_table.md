## Classification Table
| # | Scenario | QA or QC | V or Val | One-line rationale |
| --- | --- | --- | --- | --- |
| 1 | A tester executes a **regression suite** on release candidate `2.3.0` before go-live. | QC | Verification | A regression suite evaluates the product that has already been built. |
| 2 | The team adopts **trunk-based development** with **required CI checks** on every pull request. | QA | Verification | The goal of trunk-based development is to prevent defects, specifically those caused from merging branches, before they happen. |
| 3 | A **usability test** with five nurses observes whether they can complete **medication reconciliation** without training. | QC | Validation | This usability test tests the product under a real-life application. |
| 4 | A **code review** finds an **off-by-one** error before merge. | QC | Verification | A specific error is found after it has already been created. |
| 5 | The **PO** runs a **sprint review** with stakeholders and learns the **workflow** does not match **real clinic** practice. | QC | Validation | Compares existing workflow with real practice - QC/QA is slightly ambigous as the nature of the workflow's implementation is unclear. | 
| 6 | QA **updates** the **test data refresh** runbook after staging was stale for two sprints. | QC | Verification | Despite being performed by the "QA" team, this is QC because updates to the test data build off of previous testing + defects. |
| 7 | An **external auditor** requests **traceability** from requirement IDs to test case IDs for SOX scope features. | QA | N/A | Ambiguous because the test cases could relate to either how well the product performs or to its practical functionality. |
| 8 | Developers add **contract tests** between **billing** and **subscriptions** services to catch API drift early. | QA | Verification | Tests are added before any actual defects are discovered. |
| 9 | A **blameless postmortem** after an outage produces **three** action items to improve **deployment** safety. | QA | Verification | Focus is on how to prevent future outages over the specific cause of this outage - debatably dual QA/QC? |
| 10 | A tester **files** a bug: totals on the invoice PDF **do not match** the cart API response. | QC | Verification | Response to a specific found defect. |
| 11 | The team defines **Definition of Done** to include "**no open critical defects**" for sprint work. | QA | N/A | Ambiguous because the definition of a "critical defect" could be dependent on both performance metrics and the state of the final product that gets deployed. |
| 12 | Marketing runs on **A/B test** on checkout copy and picks the variant with **higher conversion**. | QC | Validation | Marketing runs work directly with end users. |

## Reflection
"QA Team" is a sort of misleading name because QA and QC are incomplete without each other. QA without QC leads to shipping a potentially defective product, and QC without QA, i.e. shutting down defects only after they are found with no preventative measures, is inefficient. Testers should have accountability for both QA and QC. 