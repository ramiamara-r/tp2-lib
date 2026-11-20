# UC-07 Register a return

Note: the template of Table 3.4 was not in the TP handout. If your Table 3.4 has other field names, keep the content and rename the fields.

Identifier: UC-07
Name: Register a return
Goal: record that a member has brought a borrowed copy back, so the loan is closed and the copy is available again (or kept aside for a reservation).
Primary actor: Librarian
Secondary actor: Mail server (notifies the member who reserved the book)
Scope: Library system
Level: user goal
Trigger: a member brings a borrowed copy back to the desk.

Preconditions:
- The librarian is authenticated.

Postconditions (success):
- The loan is closed with the return date.
- The copy is available, or kept aside if it is reserved.
- If the return was late, the member is suspended.
- If the copy was reserved, the member who reserved it has been notified.
- A receipt has been printed.

Main scenario:
1. The librarian scans the barcode of the returned copy.
2. The system finds the copy and checks that it is on loan.
3. The system closes the loan and records the return date.
4. The system checks that the return is not late and that the copy is not reserved.
5. The system marks the copy as available.
6. The system prints the receipt.

Extensions (alternative flows):
1a. The barcode cannot be read:
    1a1. The librarian enters the inventory number of the copy.
    1a2. The scenario continues at step 2.
2a. The copy is not on loan:
    2a1. The system displays an error message.
    2a2. The use case ends (no receipt is printed).
3a. The copy is returned late (extension point: late return; condition: {returned late}):
    3a1. The system runs the use case "Suspend the member".
    3a2. The scenario continues at step 4.
4a. The copy is reserved by another member:
    4a1. The system keeps the copy aside (it is not made available).
    4a2. The system asks the Mail server to notify the member who reserved it.
    4a3. The scenario continues at step 6 (step 5 is skipped).

Relationships:
- Extended by: Suspend the member (at the extension point "late return").
- Includes: none.

Business rules:
- A late return suspends the member.
- A reserved copy is never put back into free circulation.
