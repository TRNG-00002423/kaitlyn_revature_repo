'''
Loan assessment function for conditional testing demo

returns a string result indicating eligibility for the first disqualifying condition encountered
(short-circuit evaluation)

Business rules:
- Age must be 18-65 inclusive
- Annual income must be >= 25000
- Credit score must be >= 600
- Applicant must be currently employed

'''


def assess_loan(age: int, income: float, credit_score: int, employed: bool) -> str:
    '''
    Assess a loan application against eligibility criteria.
    
    Returns:
        'ELIGIBLE', 'INELIGIBLE_AGE', 'INELIGIBLE_INCOME', 'INELIGIBLE_CREDIT', or 'INELIGBLE_EMPLOYMENT'
    '''
    if age < 18 or age > 65:
        return 'INELIGIBLE_AGE'
    if income < 2500:
        return 'INELIGIBLE_INCOME'
    if credit_score < 600:
        return 'INELIGIBLE_CREDIT'
    if not employed:
        return 'INELIGIBLE_EMPLOYMENT'
    return 'ELIGIBLE'
