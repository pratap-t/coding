import click

@click.group()
def Welcome():
    """Welcome to click cli!"""
    click.echo('Welcome to click!')

@click.command()
@click.argument('name')

def greetings(name):
    click.echo('Hello, {}'.format(name))

@Welcome.command()
def goodbye(name):
    click.echo('Goodbye, {}'.format(name))

Welcome.add_command(greetings)
Welcome.add_command(goodbye)

if __name__ == '__main__':
    Welcome()