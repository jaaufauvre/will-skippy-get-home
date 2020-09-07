# frozen_string_literal: true

require './test/test_helper'
require 'skippy/kangaroo'

describe Kangaroo::Kangaroo do
  it 'should print to stdout when chortling' do
    out, = capture_io do
      Kangaroo::Kangaroo.new.chortle('Hi there!')
    end
    _(out).must_match(/Hi there!\s*/)
  end

  it 'should eventually find home' do
    # GIVEN
    grid = Grid::Grid.new(5)
    kangaroo = Kangaroo::Kangaroo.new
    home = Grid::GridItem.new
    grid.place_item(kangaroo, Grid::Point.new(0, 0))
    grid.place_item(home, Grid::Point.new(4, 4))

    # WHEN
    kangaroo.find_home(grid, home)

    # THEN
    _(grid.get_item_location(kangaroo)).must_equal(grid.get_item_location(home))
  end
end
