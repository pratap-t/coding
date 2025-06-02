import click

@click.group()
def Welcome():
    """Welcome to click cli!"""
    pass

@Welcome.command()
def greetings():
    """Sends a greeting message."""
    click.echo('Hello user')

@Welcome.command()
def goodbye():
    """Sends a goodbye to the user."""
    click.echo('Goodbye user')

if __name__ == '__main__':
    Welcome()