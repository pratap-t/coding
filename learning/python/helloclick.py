import click
@click.command()
def hello():
    click.echo('Hello click!')

if __name__ == '__main__':
        hello()