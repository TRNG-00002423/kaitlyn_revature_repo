## Manual testing

Add three contacts:
`python contact_app.py add --name Scarlett --email scarlett@example.com`
`python contact_app.py add --name Clementine --email clementine@example.com`
`python contact_app.py add --name Socks --email socks@example.com`

List all contacts:
`python contact_app.py list`
Output:
Contact 1: Name: Scarlett | scarlett@example.com
Contact 2: Name: Clementine | clementine@example.com
Contact 3: Name: Socks | socks@example.com

Get a contact:
`python contact_app.py get --id 1`
Output:
Contact 1: Name: Scarlett | scarlett@example.com

Update (name and email):
`python contact_app.py update --id 1 --name "Scarlett O'Hara" --email scarlett_o_hara@example.com`
-> Contact 1: Name: Scarlett O'Hara | scarlett_o_hara@example.com

Update (name only):
`python contact_app.py update --id 2 --name Clemmy`
-> Contact 2: Name: Clemmy | clementine@example.com

Update (email only):
`python contact_app.py update --id 3 --email king_george@example.com`
-> Contact 3: Name: Socks | king_george@example.com

Delete:
`python contact_app.py delete --id 3`
New list:
Contact 1: Name: Scarlett O'Hara | scarlett_o_hara@example.com
Contact 2: Name: Clemmy | clementine@example.com